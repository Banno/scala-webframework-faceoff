require 'rubygems'
require 'sinatra'

get '/hello/:name' do |name|
  "hello #{name}"
end
